<?php

namespace App\Http\Controllers;

use App\Models\Matiere;
use Illuminate\Http\Request;
use App\Http\Requests\MatiereRequest;

class MatiereController extends Controller
{
    public function getMatiereParClasse($id){

       
        $mat = Matiere::where('idClasse','=',$id)->get();
        return response()->json(["data" => $mat], 200);

    }

    public function createMatiere(MatiereRequest $request, $id){
        $matiere = new Matiere();
        $matiere->nom = $request->input('nom');
        $matiere->idClasse = $id;
        $matiere->save();
        return response()->json(["data" => $matiere], 201);
    }
}
