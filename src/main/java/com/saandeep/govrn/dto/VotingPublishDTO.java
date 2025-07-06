package com.saandeep.govrn.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingPublishDTO {
    @NotNull(message = "Project expected start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "Project list cannot be null")
    @NotEmpty(message = "At least 1 project is required")
    private List<Long> projectIdList;
}
