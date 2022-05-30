<?php

namespace App\Http\Controllers;

use App\Http\Requests\CategorieMatrielRequest;
use Illuminate\Http\Request;
use App\Models\CategorieMateriel;

class CategorieMaterielController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response containing the list of resources
     */
    public function show()
    {
        $Categorie = CategorieMateriel::orderBy('updated_at','desc')->get();
        if (sizeof($Categorie) > 0) {
            return response()->json(["data" => $Categorie], 200);
        } else
            return response()->json([
                "aucune Categorie"
            ], 404);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response containing the newly created resource
     */
    public function store(CategorieMatrielRequest $request)
    {
        $Categorie = CategorieMateriel::create(
            [
                "titre" => $request->titre,
            ]
        );
        return response()->json(["data" => $Categorie], 201);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response containing the updated resource
     */
    public function update(CategorieMatrielRequest $request, $id)
    {
        $Categorie = CategorieMateriel::find($id);
        if (!$Categorie) {
            return response()->json([
                "message" => "Categorie not found"
            ], 404);
        }
        $Categorie->update(
            [
                "titre" => $request->titre,
            ]
        );
        return response()->json(["data" => $Categorie], 200);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $Categorie = CategorieMateriel::find($id);
        if (!$Categorie) {
            return response()->json([
                'type' => 'DemandeCreationClub',
                'message' => 'demande non trouvée'
            ], 404);
        }
        $Categorie->delete();
        return response()->json([], 204);
    }
}
