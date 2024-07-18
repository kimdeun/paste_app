package ru.copypaste.paste.service;

import ru.copypaste.paste.api.request.PasteBoxRequest;
import ru.copypaste.paste.api.response.PasteBoxResponse;
import ru.copypaste.paste.api.response.PasteBoxURLResponse;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);

    List<PasteBoxResponse> getFirstPublicPasteBoxes();

    PasteBoxURLResponse create(PasteBoxRequest request);
}
