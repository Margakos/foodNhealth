package gr.foodNhealth.service;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Nutritionist;
import gr.foodNhealth.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class NutritionistService {

    @Autowired
    private NutritionistRepository nutritionistRepository;

}
