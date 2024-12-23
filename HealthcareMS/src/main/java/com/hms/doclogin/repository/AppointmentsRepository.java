package com.hms.doclogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.doclogin.entity.Appointment;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {

}
