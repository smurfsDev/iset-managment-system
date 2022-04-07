<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\CategorieMateriel;

class CategorieMaterielController extends Controller
{
    public function show(){
        $Categorie = CategorieMateriel::get();
        if(sizeof($Categorie)>0){
        return response()->json(["data"=>$Categorie], 200);
        }
        else
            return response()->json([
                "aucune Categorie"
            ], 404);
    }

}
