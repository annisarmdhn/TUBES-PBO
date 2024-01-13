package com.example.Toko.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "merchant")
public class Merchant implements Serializable {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private String MerchantCode;

        @Column(name = "name", length = 100)
        private String name;

        @Column(name = "location", length = 100)
        private String location;

        private boolean open;
}
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Getter
//@Setter
//@Table(name = "merchant")
//public class Merchant implements Serializable{
//        @Id // Menandakan ini adalah column pk
//        @NonNull
//        @GeneratedValue(generator = "UUID") // Column ini jika kosong akan di generate otomatis value nya
//        @GenericGenerator(
//                name = "UUID",
//                strategy = "org.hibernate.id.UUIDGenerator") // Generator value di column jika kosong
//        private String MerchantCode;
//
//        @NonNull
//        @Column(name = "MerchantName", length = 100)
//        private String MerchantName;
//
//        @NonNull
//        @Column(name = "MerchantLocation", length = 100)
//        private String MerchantLocation;
//
//        boolean open = true;
//
//}
