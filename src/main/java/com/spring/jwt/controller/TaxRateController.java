//package com.spring.jwt.controller;
//
//import com.spring.jwt.Interfaces.ITaxRate;
//import com.spring.jwt.dto.Response;
//import com.spring.jwt.dto.TaxRateDto;
//import com.spring.jwt.service.TaxRatesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/TaxRates")
//public class TaxRateController {
//
//
//
//    @Autowired
//    ITaxRate taxRateSecurity;
//
//    @Autowired
//    TaxRatesService service;
//
//
//    @GetMapping("/GetById")
//    public ResponseEntity<Response> taxRateByID(@RequestParam Integer id){
//        TaxRateDto taxRateDto=taxRateSecurity.taxratebyid(id);
//        if(taxRateDto!= null){
//            Response response=new Response("TaxRates Get By Id Successfully",taxRateDto,false);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }else {
//            Response response=new Response("TaxRate Get By Id Unsuccesfull",null,true);
//            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//
//    }
//    }
//     @PostMapping("/SaveInformation")
//    public ResponseEntity<Response> saveTaxRate(@RequestBody TaxRateDto taxRateDto){
//        TaxRateDto savedTaxRate=taxRateSecurity.savetaxrate(taxRateDto);
//        if(taxRateDto!=null){
//            Response response=new Response("Save TaxRate Succesfully",savedTaxRate,false);
//            return new ResponseEntity<>(response,HttpStatus.CREATED);
//        }else{
//            Response response=new Response("Unsuccesefull To Save The TaxRate",null,true);
//            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/GetAll")
//    public ResponseEntity<Response> getAll(){
//        try {
//
//            List<TaxRateDto> listData = taxRateSecurity.getall();
//            Response response = new Response("Get All Data Succesfully", listData, false);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }catch(Exception e){
//            Response response=new Response("Failed To Get The Data",e.getMessage(),true);
//            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//
//        }
//    }
//
//    @PatchMapping("/UpdateData")
//    public ResponseEntity<Response>updateData(@RequestParam Integer id,@RequestBody TaxRateDto taxRateDto) {
//
//            TaxRateDto taxRatesDTO = taxRateSecurity.updateData(id,taxRateDto);
//            if (taxRatesDTO != null) {
//                Response response = new Response("Update Data Succesfully", taxRatesDTO, false);
//                return new ResponseEntity<>(response, HttpStatus.CREATED);
//            } else {
//                Response response = new Response("Failed To Update Data",null, true);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        }
//    @DeleteMapping("/Delete")
//    public ResponseEntity<Response> deleteTaxRate(@RequestParam Integer id) {
//        try {
//            taxRateSecurity.deleteTaxRate(id);
//            Response response = new Response("Delete TaxRate Successfully", null, false);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            Response response = new Response("Failed To Delete TaxRate", e.getMessage(), true);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }
//}
//
//
//
