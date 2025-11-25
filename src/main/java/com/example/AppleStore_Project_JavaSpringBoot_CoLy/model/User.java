package com.example.AppleStore_Project_JavaSpringBoot_CoLy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 50,message = "Họ tên không vượt quá 50 kí tự")
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 4,max = 100, message = "Tên đăng nhập phải từ 4 - 100 kí tự")
    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(min = 3,max = 100, message = "Địa chỉ phải từ 3-100 kí tự")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0\\d{9,10})$", message = "Số điện thoại không hợp lên(Phải bắt đầu từ 0)")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Định dạnh email không hợp lệ")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu tối thiểu phải 6 kí tự")
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length=10)
    private Role role =  Role.USER;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


//get/set


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Họ tên không được để trống") @Size(max = 50, message = "Họ tên không vượt quá 50 kí tự") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Họ tên không được để trống") @Size(max = 50, message = "Họ tên không vượt quá 50 kí tự") String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank(message = "Tên đăng nhập không được để trống") @Size(min = 4, max = 100, message = "Tên đăng nhập phải từ 4 - 100 kí tự") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Tên đăng nhập không được để trống") @Size(min = 4, max = 100, message = "Tên đăng nhập phải từ 4 - 100 kí tự") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Địa chỉ không được để trống") @Size(min = 3, max = 100, message = "Địa chỉ phải từ 3-100 kí tự") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Địa chỉ không được để trống") @Size(min = 3, max = 100, message = "Địa chỉ phải từ 3-100 kí tự") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "^(0\\d{9,10})$", message = "Số điện thoại không hợp lên(Phải bắt đầu từ 0)") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "^(0\\d{9,10})$", message = "Số điện thoại không hợp lên(Phải bắt đầu từ 0)") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Email không được để trống") @Email(message = "Định dạnh email không hợp lệ") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email không được để trống") @Email(message = "Định dạnh email không hợp lệ") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Mật khẩu không được để trống") @Size(min = 6, message = "Mật khẩu tối thiểu phải 6 kí tự") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Mật khẩu không được để trống") @Size(min = 6, message = "Mật khẩu tối thiểu phải 6 kí tự") String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
