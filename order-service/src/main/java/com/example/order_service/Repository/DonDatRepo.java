package com.example.order_service.Repository;

import com.example.order_service.Entity.Dondat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonDatRepo extends JpaRepository<Dondat,Long> {
}
