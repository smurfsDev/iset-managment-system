<?php

namespace App\Http\Controllers;

use App\Models\Materiel;
use Illuminate\Http\Request;
use App\Models\DemandeMateriel;
use App\Models\CategorieMateriel;
use Illuminate\Support\Facades\DB;
use App\Models\DemandeMateriel_Materiel;

class MaterielController extends Controller
{
    public function show($id){
        $Materiel =DemandeMateriel::with('materiel')->where('id','=',$id)->get();
        if(sizeof($Materiel)>0){
        return response()->json(["data"=>$Materiel], 200);
        }
        else
            return response()->json([
                "aucun materiel"
            ], 404);
    }

    public function showMaterialsOfCategory($id){
        $Materiel = Materiel::where( 'idCategorie','=',$id)->get();
        if(sizeof($Materiel)>0){
            return response()->json(["data"=>$Materiel], 200);
            }
        else
            return response()->json([
                "aucun materiel"
            ], 404);
    }
    public function addMateriel($idMateriel ,$idDemande){
        $DemandeMateriel = DemandeMateriel::find($idDemande);
            $DemandeMateriel->materiel()->attach($idMateriel);
            return response()->json([
                'type' => 'materiel',
                'message' => 'materiel attached',
                'attached' => true
            ], 201  );
    }
    public function setQuantity(Request $request,$idMateriel ,$idDemande){
        $DemandeMateriel = DemandeMateriel::find($idDemande);
        $DemandeMateriel->materiel()->updateExistingPivot($idMateriel,['quantité'=>$request->input('quantité')]);
        $DemandeMateriel->save();
        return response()->json([
            "message"=> "quantité set"
        ], 200);
    }
    //function to delete a materiel from a demande
    public function deleteMateriel($idMateriel ,$idDemande){
        $DemandeMateriel = DemandeMateriel::find($idDemande);
        $DemandeMateriel->materiel()->detach($idMateriel);
        return response()->json([
            'type' => 'materiel',
            'message' => 'materiel detached',
            'attached' => false
        ], 201);
    }
    // public function update($idMateriel, $idDemande){
    //     $DemandeMateriel = DemandeMateriel_Materiel::where('idDemande','=',$idDemande)->firstOrFail();
    //     if(isset($DemandeMateriel)){
    //             $DemandeMateriel->idDemande=$idDemande;
    //             $DemandeMateriel->idMateriel=$idMateriel;
    //             $DemandeMateriel->quantité=1;
    //             $DemandeMateriel->save();
    //             return response()->json([
    //                 'message' => 'Update Success',
    //                 'id' => $DemandeMateriel->id,
    //                 'attributes' => $DemandeMateriel
    //         ], 201);
    //     }
    //     else return response()->json([
    //             "aucun materiel"
    //         ], 404);
    // }
    // public function addMateriel(Request $request,$idMateriel, $idDemande){
    //     $DemandeMateriel = DemandeMateriel_Materiel::get();
    //     $data = array(
    //         "idDemande"=> $idDemande,
    //         "idMateriel"=> $idMateriel,
    //         "quantité"=>$DemandeMateriel->quantité->$request->input('quantité'),
    //     );
    //     $DemandeMateriel = DemandeMateriel_Materiel::create($data);
    //     if ($DemandeMateriel) {
    //         return response()->json([
    //             'data' => [
    //                 'message' => 'Success',
    //                 'id' => $DemandeMateriel->id,
    //                 'attributes' => $DemandeMateriel
    //             ]
    //         ], 201);
    //     }
    //     return "Success";
    // }
    // public function deleteMateriel($idMateriel, $idDemande){
    //     $DemandeMateriel = DemandeMateriel_Materiel::where('idDemande','=',$idDemande)->where('idMateriel','=',$idMateriel)->firstOrFail();
    //     if(isset($DemandeMateriel)){
    //         $DemandeMateriel->delete();
    //         return response()->json([
    //             'data' => [
    //                 'message' => 'Success',
    //                 'id' => $DemandeMateriel->id,
    //                 'attributes' => $DemandeMateriel
    //             ]
    //         ], 201);
    //     }
    //     return "Success";
    // }

    // public function showMaterialsOfDemande($id){
    //     $Materiel = DemandeMateriel_Materiel::with('materiel')->where('idDemande','=',$id)->get();
    //     if(sizeof($Materiel)>0){
    //         return response()->json(["data"=>$Materiel], 200);
    //         }
    //     else
    //         return response()->json([
    //             "aucun materiel"
    //         ], 404);
    // }
}
