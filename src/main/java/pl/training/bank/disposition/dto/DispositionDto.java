package pl.training.bank.disposition.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "disposition")
@Data
public class DispositionDto {

    private String accountNumber;
    private long funds;
    private String operationName;

}
