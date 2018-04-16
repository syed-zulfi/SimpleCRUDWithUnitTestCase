package com.apps.sample.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Guest")
@ToString(callSuper = true)
@EqualsAndHashCode
public class Guest implements Serializable {
    @Id
    @GeneratedValue
     @Getter@Setter private Long id;
     @NonNull@Getter@Setter private String name;
     @NonNull@Getter@Setter private String phone;



}
