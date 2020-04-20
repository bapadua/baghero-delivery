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

    @Query("SELECT l.qrCode FROM LocationDelivery l WHERE l.id = :id AND l.status = '0'")
    Optional<QrCode> findByIdAndStatus(@Param("id") String id);
}
