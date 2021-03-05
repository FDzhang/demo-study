package com.example.demoreadconfigproperties.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

@Getter
@Setter
@ToString
@ConfigurationProperties("my-profile")
@Validated
public class ProfileProperties {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    private Boolean handsome = Boolean.TRUE;

    public static void main(String[] args) {
        int capacity = 3;
        int[] x = new int[capacity];
        for (int i = 0; i < x.length; i++) {
            x[i]=i;
        }
        int newCapacity = capacity * 2;
        x = Arrays.copyOf(x, newCapacity);
        capacity = newCapacity;

        System.out.println(Arrays.toString(x));
        System.out.println(capacity);
    }
}