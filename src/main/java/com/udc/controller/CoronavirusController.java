package com.udc.controller;

import com.udc.model.Coronavirus;
import com.udc.model.Dashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sdrahnea
 */
@RestController
@RequestMapping(value = "/coronavirus")
public class CoronavirusController extends AbstractController<Coronavirus> {

}