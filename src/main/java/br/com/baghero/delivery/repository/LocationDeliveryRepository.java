package br.com.baghero.delivery.repository;


import br.com.baghero.delivery.entity.LocationDelivery;
import br.com.baghero.delivery.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationDeliveryRepository extends JpaRepository<LocationDelivery, String> {

    @Query("SELECT l.qrCode FROM LocationDelivery l WHERE l.location = :id")
    Optional<QrCode> findQrCode(@Param("id") String id);

}
