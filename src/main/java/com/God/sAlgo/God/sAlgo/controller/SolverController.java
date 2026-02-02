package com.God.sAlgo.God.sAlgo.controller;

import com.God.sAlgo.God.sAlgo.controller.dto.CubeColorRequest;
import com.God.sAlgo.God.sAlgo.controller.dto.SolveRequest;
import com.God.sAlgo.God.sAlgo.controller.dto.SolveResponse;
import com.God.sAlgo.God.sAlgo.service.SolverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solve")
public class SolverController {

    private final SolverService solverService;

    public SolverController(SolverService solverService){
        this.solverService=solverService;
    }

    @PostMapping
    public SolveResponse solve(@RequestBody SolveRequest request){
        String solution = solverService.solve(request.getScramble());
        return new SolveResponse(solution);
    }

    @PostMapping("/colors")
    public SolveResponse solveFromColors(@RequestBody CubeColorRequest request){
        String solution = solverService.solveFromColors(request.getFaces());

        return new SolveResponse(solution);
    }


}
