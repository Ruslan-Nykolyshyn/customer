package com.task.customer.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private  long id;

    private String fullName;

    private  String email;

    private String phone;

    private  long created;

    private long updated;

    private  boolean is_active;



}
