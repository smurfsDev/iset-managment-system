<?php

namespace App\Http\Controllers;

use App\Models\bultin;
use Illuminate\Http\Request;

class BultinController extends Controller
{
    public function store(Request $request)
    {

        $bultin = bultin::create([
            'student_id' => $request->student_id,
            'moyenne' => $request->moyenne,
        ]);

        return response()->json($bultin, 201);
    }
}
