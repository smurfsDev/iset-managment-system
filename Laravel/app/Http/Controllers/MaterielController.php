<?php

namespace App\Http\Controllers;

use App\Http\Requests\MaterielEditRequest;
use App\Http\Requests\MaterielRequest;
use App\Models\Materiel;
use Illuminate\Http\Request;
use App\Models\DemandeMateriel;
use App\Models\User;

class MaterielController extends Controller
{
    /**
     * Display a listing of the materiels.
     *
     * @return \Illuminate\Http\Response containing the list of resources
     */
    public function get(Request $request)
    {
        $materiel = $request->user()->materiel()->with('categorie')->orderBy('updated_at','desc')->paginate(5);
        if (!empty($materiel)) {
            return response()->json(["data" => $materiel], 200);
        } else {
            return response()->json([
                "aucun materiel"
            ], 404);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @return \Illuminate\Http\Response containing the list of resources
     */
    public function create(MaterielRequest $request)
    {
        $materiel = new Materiel();
        $materiel->titre = $request->titre;
        $materiel->quantité = $request->quantité;
        $materiel->description = $request->description;
        $materiel->idCategorie = $request->idCategorie;
        $materiel->idResponsable = $request->user()->id;
        $materiel->save();
        return response()->json(["data" => $materiel], 201);
    }

    /**
     * update an existing resource in storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(MaterielEditRequest $request, $id)
    {
        $materiel = Materiel::find($id);
        if (!empty($materiel)) {
            $materiel->titre = $request->titre;
            $materiel->quantité = $request->quantité;
            $materiel->description = $request->description;
            $materiel->idCategorie = $request->idCategorie;
            $materiel->idResponsable = $request->user()->id;
            $materiel->save();
            return response()->json(["data" => $materiel], 200);
        } else {
            return response()->json([
                "aucun materiel"
            ], 404);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function delete($id)
    {
        $materiel = Materiel::find($id);
        if (!empty($materiel)) {
            $materiel->delete();
            return response()->json(["data" => $materiel], 200);
        } else {
            return response()->json([
                "aucun materiel"
            ], 404);
        }
    }


    public function show($id)
    {
        $Materiel = DemandeMateriel::with('materiel')->where('id', '=', $id)->get();
        if (sizeof($Materiel) > 0) {
            return response()->json($Materiel, 200);
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
            ],
             404);
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

}
