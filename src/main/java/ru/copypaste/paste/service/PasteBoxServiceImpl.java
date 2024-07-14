package ru.copypaste.paste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.copypaste.paste.api.request.AccessStatus;
import ru.copypaste.paste.api.request.PasteBoxRequest;
import ru.copypaste.paste.api.response.PasteBoxResponse;
import ru.copypaste.paste.api.response.PasteBoxURLResponse;
import ru.copypaste.paste.repository.PasteBoxEntity;
import ru.copypaste.paste.repository.PasteBoxRepository;
import ru.copypaste.paste.repository.PasteBoxRepositoryMap;
import ru.copypaste.paste.utils.HexHashGenerator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties(prefix = "app")
public class PasteBoxServiceImpl implements PasteBoxService {
    private String host;
    private int publicListSize;
    private final PasteBoxRepository pasteBoxRepository;
    @Autowired
    public PasteBoxServiceImpl(PasteBoxRepository pasteBoxRepository) {
        this.pasteBoxRepository = pasteBoxRepository;
    }

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = pasteBoxRepository.getByHash(hash);

        return new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBoxes() {
        List<PasteBoxEntity> list = pasteBoxRepository.getListOfPublicAndAlive(publicListSize);

        return list.stream().map(pasteBoxEntity ->
            new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                    .collect(Collectors.toList());
    }

    @Override
    public PasteBoxURLResponse create(PasteBoxRequest request) {
        int hash = HexHashGenerator.generateId();
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setHash(HexHashGenerator.getHexHash());
        pasteBoxEntity.setPublic(request.getAccessStatus() == AccessStatus.PUBLIC);
        pasteBoxEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));

        pasteBoxRepository.add(pasteBoxEntity);
        return new PasteBoxURLResponse(host + "/" + HexHashGenerator.getHexHash());
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPublicListSize(int publicListSize) {
        this.publicListSize = publicListSize;
    }
}
