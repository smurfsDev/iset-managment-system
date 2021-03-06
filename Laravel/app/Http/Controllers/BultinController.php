<?php

namespace App\Http\Controllers;

use App\Models\bultin;
use Illuminate\Http\Request;

class BultinController extends Controller
{
    /**
     * Store a newly created resource in storage.
     *
     * @return \Illuminate\Http\Response containing the newly created resource
     */
    public function store(Request $request)
    {

        $bultin = bultin::create([
            'student_id' => $request->student_id,
            'moyenne' => $request->moyenne,
            'numero_bultin' => $request->numero_bultin,
        ]);

        return response()->json($bultin, 201);
    }
}
