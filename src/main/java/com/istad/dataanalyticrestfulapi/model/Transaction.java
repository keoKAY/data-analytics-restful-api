package com.istad.dataanalyticrestfulapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private int id;
//   private int senderAccountId;

    private UserTransaction sender ;
//    private int receiverAccountId;
    private UserTransaction receiver;
    private float amount;
    private String remark;
   @JsonFormat(pattern = "yyyy-MM-dd  HH:mm")
    private Timestamp transferAt;
}
