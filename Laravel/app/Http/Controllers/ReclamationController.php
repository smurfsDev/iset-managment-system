<?php

namespace App\Http\Controllers;

use App\Models\Reclamation;
use Illuminate\Http\Request;

class ReclamationController extends Controller
{

    public function getReclamation(Request $request){
            if ($request->user()->roles()->get()->contains('name', "admin")) {
            $reclamtion = Reclamation::orderBy('created_at', 'desc')
                ->paginate(5);
            if (empty($reclamtion)) {
                return response()->json(['message' => 'No reclamation found'], 404);
            }
            return response()->json($reclamtion, 200);
        }else{
            $reclamtion = $request->user()->Reclamation()->orderBy('updated_at', 'desc')->paginate(5);
            if (empty($reclamtion)) {
                return response()->json(['message' => 'No reclamation found'], 404);
            }
            return response()->json($reclamtion, 200);
        }
    
    }
    
    public function createReclamation(){
          $reclamation = new Reclamation();
          $reclamation->Titre = "Titre";
          $reclamation->Description = "Description";
          $reclamation->type = "type";
          $reclamation->idAdmin = "idAdmin";
          $reclamation->save();
          
            return response()->json($reclamation, 201);
                
          
    }
  

    public function show($id){
        $reclamations = Reclamation::orderBy('updated_at', 'desc')->paginate(5);
                if (sizeof($reclamations) > 0)
                    return response()->json(
                        $reclamations,
                        200
                    );
                else
                    return response()->json([], 404);

    }
    
    public function showAll()
    {
        $Reclamation = Reclamation::all();
        if (sizeof($Reclamation) > 0) {
            return response()->json(["data" => $Reclamation], 200);
        } else
            return response()->json([
                "aucun reclamations"
            ],
             404);
    }

    public function addReclamation(Request $request)
    {
        $Reclamation = new Reclamation();
        $Reclamation->titre = $request->input('titre');
        $Reclamation->description = $request->input('description');
        $Reclamation->type = $request->input('type');
        $Reclamation->idResponsable = $request->user()->id;
        $Reclamation->save();
        return response()->json([
            'type' => 'reclamation',
            'message' => 'recalamation added',
            'attached' => true,
            "data" => Reclamation::find($Reclamation->id)
        ], 201);
    }

    public function deleteReclamation($id)
    {
        $Reclamation = Reclamation::find($id);
        $Reclamation->delete();
        return response()->json([
            'type' => 'reclamation',
            'message' => 'recalamation deleted',
            'attached' => true,
            "data" => Reclamation::find($id)
        ], 201);
    }

    public function updateReclamation(Request $request,$id){
        $reclamations = Reclamation::find($id);
        if ($reclamations) {

            $reclamations->titre = $request->input('titre') ? $request->input('titre') : $reclamations->titre;
            $reclamations->description = $request->input('description') ? $request->input('description') : $reclamations->description;
            $reclamations->type = $request->input('type') ? $request->input('type') : $reclamations->type;
            
            $reclamations->save();
            return response()->json([
                'message' => 'reclamation mis à jour',
                'id' => $reclamations->id,
                'attributes' => $reclamations
            ], 201);
        } else {
            return response()->json([
                "reclamation n existe pas"
            ], 404);
        }
    
    }

}
