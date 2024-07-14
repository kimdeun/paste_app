package ru.copypaste.paste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.copypaste.paste.api.request.PasteBoxRequest;
import ru.copypaste.paste.api.response.PasteBoxResponse;
import ru.copypaste.paste.api.response.PasteBoxURLResponse;
import ru.copypaste.paste.service.PasteBoxService;

import java.util.List;

@RestController
public class PasteBoxController {
    private final PasteBoxService pasteBoxService;

    @Autowired
    public PasteBoxController(PasteBoxService pasteBoxService) {
        this.pasteBoxService = pasteBoxService;
    }

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {
        return pasteBoxService.getByHash(hash);
    }

    @GetMapping("/")
    public List<PasteBoxResponse> getPublicPasteList() {
        return pasteBoxService.getFirstPublicPasteBoxes();
    }

    @PostMapping("/")
    public PasteBoxURLResponse addNewPaste(@RequestBody PasteBoxRequest request) {
        return pasteBoxService.create(request);
    }
}
