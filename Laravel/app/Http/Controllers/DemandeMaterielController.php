<?php

namespace App\Http\Controllers;

use App\Models\DemandeMateriel;
use Illuminate\Http\Request;

class DemandeMaterielController extends Controller
{
    public function create(Request $request){
        $dateEmploi = $request->input('dateEmploi');
        $dateDeRemise = $request->input('dateDeRemise');
        $idResponsableClub = $request->input('idResponsableClub');
        $idDestinataire = $request->input('idDestinataire');
        $data = array(
            "idResponsableClub"=> $idResponsableClub,
            "idDestinataire"=> $idDestinataire,
            "dateEmploi"=> $dateEmploi,
            "dateDeRemise"=> $dateDeRemise,
        );
        $DemandeMateriel = DemandeMateriel::create($data);
        if ($DemandeMateriel) {
            return response()->json([
                'data' => [
                    'message' => 'Success',
                    'id' => $DemandeMateriel->id,
                    'attributes' => $DemandeMateriel
                ]
            ], 201);
        }
        return "Success";
    }
    public function show(){
        $DemandeMateriel = DemandeMateriel::paginate(5);
        if(sizeof($DemandeMateriel)>0){
        return response()->json($DemandeMateriel, 200);
        }
        else
            return response()->json([
                "aucune demande"
            ], 404);
    }
    public function update(Request $request ,$id){
        $DemandeMateriel = DemandeMateriel::find($id);
        if($DemandeMateriel){
            $DemandeMateriel->idDestinataire= $request->input('idDestinataire')?$request->input('idDestinataire'):$DemandeMateriel->idDestinataire;
            $DemandeMateriel->dateEmploi= $request->input('dateEmploi')?$request->input('dateEmploi'):$DemandeMateriel->dateEmploi;
            $DemandeMateriel->dateDeRemise= $request->input('dateDeRemise')?$request->input('dateDeRemise'):$DemandeMateriel->dateDeRemise;
            $DemandeMateriel->save();
            return response()->json([
                'message' => 'Update Success',
                'id' => $DemandeMateriel->id,
                'attributes' => $DemandeMateriel
        ], 201);
        }else{
            return response()->json([
                "demande non trouvée"
            ], 404);
    }
    }
    public function delete($id){
        $DemandeMateriel = DemandeMateriel::find($id);
        if ($DemandeMateriel) {
            $DemandeMateriel->delete();
            return response()->json([
            ], 204);
        } else {
            return response()->json([
                'type' => 'DemandeMateriel',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
}
