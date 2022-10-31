package by.dmitry.shelepen.task3.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class ReactorOutputDto {
    private String output;
    private String time;


    public ReactorOutputDto(String output, String time) {
        this.output = output;
        this.time = time;
    }

    public ReactorOutputDto() {
    }


    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

