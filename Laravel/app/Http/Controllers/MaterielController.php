<?php

namespace App\Http\Controllers;

use App\Models\Materiel;
use Illuminate\Http\Request;

class MaterielController extends Controller
{
    public function show(){
        $Materiel = Materiel::paginate(5);
        if(sizeof($Materiel)>0){
        return response()->json($Materiel, 200);
        }
        else
            return response()->json([
                "aucun materiel"
            ], 404);
    }
}
