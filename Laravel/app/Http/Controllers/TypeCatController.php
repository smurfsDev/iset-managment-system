<?php

namespace App\Http\Controllers;

use App\Models\TypeCat;
use Illuminate\Http\Request;

class TypeCatController extends Controller
{
    public function get()
    {
        $type = TypeCat::all();
        if ($type->isEmpty()) {
            return response()->json(['message' => 'Aucune catégorie de document n\'est disponible'], 404);
        }
        return response()->json($type, 200);
    }
    public function showTypeCat()
    {
        $TC = TypeCat::orderBy("updated_at", "desc")->paginate(5);
        if (sizeof($TC) > 0) {
            return response()->json(["data" => $TC], 200);
        } else
            return response()->json([
                "aucune Categorie"
            ], 404);
    }

    function createTypeCat(Request $request)
    {
        $idResponsable = $request->user()->id;
        $title = $request->input('title');

        $newTC = array(
            "title" => $title,
            "idResponsable" => $idResponsable
        );

        $createNewTC = TypeCat::create($newTC);

        if ($createNewTC) {
            return response()->json([
                'data' => [
                    'message' => 'Categorie Ajouté avec succès',
                    'id' => $createNewTC->id,
                    'attributes' => $createNewTC
                ]
            ], 201);
        }
        return "Categorie Ajouté avec succès";
    }

    public function updateTypeCat($id, Request $request)
    {

        $TC = TypeCat::find($id);
        if ($TC) {

            $TC->title = $request->input('title') ? $request->input('title') : $TC->title;

            $TC->save();
            return response()->json([
                'message' => 'Categorie mis à jour',
                'id' => $TC->id,
                'attributes' => $TC
            ], 201);
        } else {
            return response()->json([
                "Categorie n'existe pas"
            ], 404);
        }
    }

    public function deleteTypeCat($id)
    {

        $TC = TypeCat::find($id);

        if ($TC) {
            $TC->delete();
            return response()->json([
                'type' => 'CategorierReclamation',
                'message' => 'Categorie Supprimé! '
            ], 204);
        } else {
            return response()->json([
                'type' => 'CategorieReclamation',
                'message' => "Categorie n'existe pas"
            ], 404);
        }
    }
}
