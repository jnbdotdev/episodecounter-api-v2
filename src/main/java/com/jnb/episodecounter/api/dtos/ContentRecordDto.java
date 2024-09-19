package com.jnb.episodecounter.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContentRecordDto(@NotBlank String name, @NotNull int content_group, @NotNull int content_unit, String platform, int releaseDay, boolean releasing, @NotNull int personalStatus, @NotNull int category) {
}
