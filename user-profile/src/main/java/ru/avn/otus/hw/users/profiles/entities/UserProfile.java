package ru.avn.otus.hw.users.profiles.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = UserProfile.TABLE_NAME)
@EqualsAndHashCode(of = {"id", "username"})
public class UserProfile {

    protected static final String TABLE_NAME = "user_profiles";
    private static final String SEQUENCE_NAME = "id_" + TABLE_NAME + "_seq";
    private static final String SEQUENCE_GENERATOR_NAME = TABLE_NAME + "_seq_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @Column(length = 256)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Version
    private int version;
}
