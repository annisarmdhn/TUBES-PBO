package com.example.Toko.service;

import com.example.Toko.model.Merchant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {
    Merchant addMerchant(Merchant merchant);
    Merchant updateMerchant(Merchant merchant);
    void deleteMerchant(String MerchantCode);
    List<Merchant> getAllMerchant();
    Merchant getMerchantDetail(String selectedMerchantCode);
}
