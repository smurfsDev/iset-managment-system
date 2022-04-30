<?php

namespace App\Http\Controllers;

use App\Models\Departement;
use Illuminate\Http\Request;

class DepartementController extends Controller
{
    public function index(){
        $departement=Departement::get();
          if(sizeof($departement)>0){
               return response()->json(["data"=>$departement], 200);
          }
          else
               return response()->json([
                 "aucun departement"
               ], 404);
    }
}
