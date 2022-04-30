<?php

namespace App\Http\Controllers;

use App\Models\Salle;
use Illuminate\Http\Request;

class SalleController extends Controller
{
   public function index($id){
       $salle=Salle::where('departement_id','=',$id)->get();
         if(sizeof($salle)>0){
              return response()->json(["data"=>$salle], 200);
         }
         else
              return response()->json([
                "aucune salle"
              ], 404);
   }
}
