package pl.training.bank.common.aop;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class ResultPage<T> {

    @NonNull
    private List<T> data;
    private int pageNumber;
    private int totalPages;

}
