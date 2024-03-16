package brito.org.com.corporatelocallybackend.exception.handler;

import brito.org.com.corporatelocallybackend.exception.model.EntidadeNaoEncontradaException;
import brito.org.com.corporatelocallybackend.exception.model.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageSource messageSource;

    private static final String MSG_ERRO_USUARIO_FINAL
            = "Ocorreu um erro interno inesperado do sistema. Tente novamente e se o "
            + "problema persistir, entre em contato com o administrador do sistema";

    private static final String MSG_DADOS_INVALIDOS
            ="Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

    private static final String MSG_ERRO_VALIDACAO
            ="Há um erro de validação na requisição. Por favor corrija.";


    private Problem.ProblemBuilder createProblemBuilder (HttpStatus status,
                                                         ProblemType problemType,
                                                         String detail){

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(LocalDateTime.now());
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(
            Exception e, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_VALIDACAO;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_VALIDACAO)
                .build();


        return handleExceptionInternal(e, problem, new HttpHeaders(),
                status, request);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEstadoNaoEncontradoException(
            EntidadeNaoEncontradaException e, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();


        return handleExceptionInternal(e, problem, new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(
            EntidadeNaoEncontradaException e, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();


        return handleExceptionInternal(e, problem, new HttpHeaders(),
                status, request);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?>handleUncaught (Exception e, WebRequest request) {

        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = MSG_ERRO_USUARIO_FINAL;
        e.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

}