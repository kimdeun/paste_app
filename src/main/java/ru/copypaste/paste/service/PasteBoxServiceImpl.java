package ru.copypaste.paste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.copypaste.paste.api.request.AccessStatus;
import ru.copypaste.paste.api.request.PasteBoxRequest;
import ru.copypaste.paste.api.response.PasteBoxResponse;
import ru.copypaste.paste.api.response.PasteBoxURLResponse;
import ru.copypaste.paste.repository.PasteEntity;
import ru.copypaste.paste.repository.PasteBoxRepository;
import ru.copypaste.paste.utils.HexHashGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties(prefix = "app")
public class PasteBoxServiceImpl implements PasteBoxService {
    private String host;
    private int publicListSize;
    private final PasteBoxRepository pasteBoxRepository;

    @Autowired
    public PasteBoxServiceImpl(@Qualifier("PasteBoxRepositoryMySQL") PasteBoxRepository pasteBoxRepository) {
        this.pasteBoxRepository = pasteBoxRepository;
    }

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteEntity pasteEntity = pasteBoxRepository.getByHash(hash);

        return new PasteBoxResponse(pasteEntity.getData(), pasteEntity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBoxes() {
        List<PasteEntity> list = pasteBoxRepository.getListOfPublicAndAlive(publicListSize);

        return list.stream().map(pasteEntity ->
                        new PasteBoxResponse(pasteEntity.getData(), pasteEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteBoxURLResponse create(PasteBoxRequest request) {
        int hash = HexHashGenerator.generateId();
        PasteEntity pasteEntity = new PasteEntity();
        pasteEntity.setData(request.getData());
        pasteEntity.setId(hash);
        pasteEntity.setHash(HexHashGenerator.getHexHash());
        pasteEntity.setPublic(request.getAccessStatus() == AccessStatus.PUBLIC);
        pasteEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));

        pasteBoxRepository.add(pasteEntity);
        return new PasteBoxURLResponse(host + "/" + HexHashGenerator.getHexHash());
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPublicListSize(int publicListSize) {
        this.publicListSize = publicListSize;
    }
}
