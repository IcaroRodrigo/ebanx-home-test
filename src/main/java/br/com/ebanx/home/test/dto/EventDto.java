package br.com.ebanx.home.test.dto;

import java.math.BigDecimal;

public record EventDto(String type, Integer destination, BigDecimal amount) {
}
