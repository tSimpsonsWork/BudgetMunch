//package com.example.project2.ArelysBudgetMunch;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
////@Service has the business logic. It marks the class as a service provider
////-----Step 2 or 3: Budget Service----------
//@Service
//public class BudgetService {
//
//    private final BudgetRepository budgetRepository;
//
//    @Autowired//connects to repository bean
//    public BudgetService(BudgetRepository budgetRepository) {
//        this.budgetRepository = budgetRepository;
//    }
//    public List<Budget> getBudgetInfo(){
//        return budgetRepository.findAll();
//    }
//
//    //add a Restaurant by max price
//    public void addRestaurant(Budget budget){
//        Optional<Budget> restaurantOptional = budgetRepository
//                .findRestaurantByBudgetPrice(budget.getMaxPrice());
//        if (restaurantOptional.isPresent()){
//            throw new IllegalStateException("Restaurant already in list");
//        }
//        budgetRepository.save(budget);
//        //System.out.println(budget);
//    }
//    //delete a restaurant
//    public void deleteRestaurant(Long restaurantId){
//        boolean exists = budgetRepository.existsById(restaurantId);
//        if(!exists){
//            throw new IllegalStateException(
//                    "restaurant"+ restaurantId + "does not exist"
//            );
//        }
//        budgetRepository.deleteById(restaurantId);
//
//    }
//
//    @Transactional
//    public void updateRestaurant(Long restaurantId,
//                                 String restaurantName,
//                                 Double maxPrice) {
//        Budget budget = budgetRepository.findById(restaurantId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "restaurant with id " + restaurantId + " does not exist"
//                ));
//
//        if(restaurantName != null &&
//                restaurantName.length() > 0 &&
//                !Objects.equals(budget.getRestaurantName(), restaurantName)){
//            budget.setRestaurantName(restaurantName);
//        }
//        if(maxPrice != null &&
//                maxPrice > 0 &&
//                !Objects.equals(budget.getRestaurantName(), maxPrice)){
//            Optional<Budget> budgetOptional = budgetRepository
//                    .findRestaurantByBudgetPrice(maxPrice);
//            if(budgetOptional.isPresent()){
//                throw new IllegalStateException("email taken");
//            }
//            budget.setMaxPrice(maxPrice);
//        }
//
//    }
//
//}
