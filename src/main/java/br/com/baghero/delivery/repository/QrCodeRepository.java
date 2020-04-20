package br.com.baghero.delivery.repository;


import br.com.baghero.delivery.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, String> {
}
