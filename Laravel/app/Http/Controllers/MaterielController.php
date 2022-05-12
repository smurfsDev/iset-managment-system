<?php

namespace App\Http\Controllers;

use App\Models\Materiel;
use Illuminate\Http\Request;
use App\Models\DemandeMateriel;
use App\Models\User;

class MaterielController extends Controller
{
    public function show($id)
    {
        $Materiel = DemandeMateriel::with('materiel')->where('id', '=', $id)->get();
        if (sizeof($Materiel) > 0) {
            return response()->json(["data" => $Materiel], 200);
        } else
            return response()->json([
                "aucun materiel"
            ], 404);
    }

    public function showMaterialsOfCategory($id)
    {
        $Materiel = Materiel::where('idCategorie', '=', $id)->get();
        if (sizeof($Materiel) > 0) {
            return response()->json(["data" => $Materiel], 200);
        } else
            return response()->json([
                "aucun materiel"
            ], 404);
    }

    public function addMateriel($idMateriel, $idDemande)
    {
        $DemandeMateriel = DemandeMateriel::find($idDemande);
        $DemandeMateriel->materiel()->attach($idMateriel);
        return response()->json([
            'type' => 'materiel',
            'message' => 'materiel attached',
            'attached' => true,
            "data" => DemandeMateriel::with('materiel')->find($idDemande)
        ], 201);
    }

    public function setQuantity(Request $request, $idMateriel, $idDemande)
    {
        $DemandeMateriel = DemandeMateriel::find($idDemande);
        $DemandeMateriel->materiel()->updateExistingPivot($idMateriel, ['quantité' => $request->input('quantité')]);
        $DemandeMateriel->save();
        return response()->json([
            "message" => "quantité set"
        ], 200);
    }

    public function deleteMateriel($idMateriel, $idDemande)
    {
        $DemandeMateriel = DemandeMateriel::find($idDemande);
        $DemandeMateriel->materiel()->detach($idMateriel);
        return response()->json([
            'type' => 'materiel',
            'message' => 'materiel detached',
            'attached' => false
        ], 201);
    }
    public function getAllResponsableMateriel(){
        $Responsable = User::whereHas('materiel')->get();
        if (sizeof($Responsable) > 0) {
            return response()->json(["data" => $Responsable], 200);
        } else
            return response()->json([
                "aucun responsable"
            ], 404);
    }
    // public function getAllResponsableMateriel(){
    //     $Responsable = Materiel::with('responsable')->get();
    //     if (sizeof($Responsable) > 0) {
    //         return response()->json(["data" => $Responsable], 200);
    //     } else
    //         return response()->json([
    //             "aucun responsable"
    //         ], 404);
    // }
}
