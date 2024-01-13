package com.example.Toko.service;

import com.example.Toko.model.Merchant;
import com.example.Toko.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant addMerchant(Merchant merchant) {
        try {
//            log.info("Attempting to save merchant: {}", merchant);
            Merchant savedMerchant = merchantRepository.save(merchant);
//            log.info("Merchant saved successfully: {}", savedMerchant);
            return savedMerchant;
        } catch (Exception e) {
            log.error("Error adding merchant: {}", e.getMessage(), e);
            // Handle the exception or rethrow it
            return null;
        }
    }


    @Override
    public Merchant updateMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(String MerchantCode) {
        merchantRepository.deleteById(MerchantCode);
    }

    @Override
    public List<Merchant> getAllMerchant() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant getMerchantDetail(String selectedMerchantCode) {
        log.info("Getting merchant detail info of {}", selectedMerchantCode);
        return Optional.ofNullable(merchantRepository.findById(selectedMerchantCode))
                .map(merchant -> Merchant.builder()
                        .MerchantCode(merchant.get().getMerchantCode())
                        .name(merchant.get().getName())
                        .location(merchant.get().getLocation())
                        .open(true)
                        .build())
                .orElse(null);
    }

}
