package pl.training.bank.disposition.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.training.bank.common.mapper.Mapper;
import pl.training.bank.disposition.dto.DispositionDto;
import pl.training.bank.disposition.entity.Disposition;
import pl.training.bank.disposition.service.DispositionService;

import javax.validation.Valid;

@RequestMapping("/api/v1/dispositions")
@RequiredArgsConstructor
@RestController
public class DispositionController {

    @NonNull
    private DispositionService dispositionService;
    @NonNull
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity process(@RequestBody @Valid DispositionDto dispositionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Disposition disposition = mapper.map(dispositionDto, Disposition.class);
        dispositionService.process(disposition);
        return ResponseEntity.noContent().build();
    }

}
