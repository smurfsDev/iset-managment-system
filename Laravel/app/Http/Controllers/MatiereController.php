<?php

namespace App\Http\Controllers;

use App\Models\Matiere;
use Illuminate\Http\Request;

class MatiereController extends Controller
{
    public function getMatiereParClasse($id){

       
        $mat = Matiere::where('idClasse','=',$id)->get();
        return response()->json(["data" => $mat], 200);

    }
}
