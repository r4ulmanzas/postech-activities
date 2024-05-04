package postech.soat.api;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ApiResponse<T> {
    public T data;
    public Status status;
    public List<ApiError> errors;

    public ApiResponse(T data) {
        this.data = data;
        this.status = Status.SUCCESS;
    }

    public ApiResponse(List<ApiError> errors) {
        this.errors = errors;
        this.status = Status.ERROR;
    }
}
