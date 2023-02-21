package com.test.bankapi.dto.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordDto {
    private String currentPassword;
    private String newPassword;
}
