//package com.example.project2.ArelysBudgetMunch;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
////@RestController simplifies the creation of RESTful web services
////Allows the class to handle requests made by the client.
////It helps us handle REST APIs such as GET, POST, Delete, and Put requests
//@RestController
//@RequestMapping(path = "api/v1/budget")
////-------Step 2: Budget Controller-------------
//public class BudgetController {
//
//    private final BudgetService budgetService;
//
//    @Autowired //automatic dependency injection from Beans...This Controller connects to Service
//    public BudgetController(BudgetService budgetService) {
//        this.budgetService = budgetService;
//    }
//
//    @GetMapping
//    public List <Budget> getBudgetInfo(){
//        return budgetService.getBudgetInfo();
//    }
//    //add restaurant info according to budget
//    @PostMapping
//    public void budgetRestaurant(@RequestBody Budget budget){
//        budgetService.addRestaurant(budget);
//    }
//
//    @DeleteMapping(path="{restaurantId}")
//    public void deleteRestaurant(@PathVariable("restaurantId") Long restaurantId){
//        budgetService.deleteRestaurant(restaurantId);
//    }
//
//    @PutMapping(path="{restaurantId}")
//    public void updateRestaurant(
//            @PathVariable("restaurantId") Long restaurantId,
//            @RequestParam(required = false) String restaurantName,
//            @RequestParam(required = false) Double maxPrice){
//        budgetService.updateRestaurant(restaurantId, restaurantName, maxPrice);
//    }
//}
//
//
//
//
