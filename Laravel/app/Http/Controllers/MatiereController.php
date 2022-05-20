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

    public function updateMatiere(MatiereRequest $request, $id){
        $matiere = Matiere::find($id);
        if ($matiere) {
            $matiere->nom = $request->input('nom') ? $request->input('nom') : $matiere->nom;
             $matiere->save();
            return response()->json([
                'message' => 'Update Success',
                'id' => $matiere->id,
                'attributes' => $matiere
            ], 201);
        } else {
            return response()->json([
                "Matiere non trouvée"
            ], 404);
        }
    }

    public function deleteMatiere($id){
        $matiere = Matiere::find($id);
        if ($matiere) {
            $matiere->delete();
            return response()->json([], 204);
        } else {
            return response()->json([
                'type' => 'matiere',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
}
