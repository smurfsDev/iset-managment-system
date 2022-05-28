<?php

namespace App\Http\Controllers;

use App\Models\EmploieDeTemp;
use Illuminate\Http\Request;

class EmploieDeTempController extends Controller
{

    public function getEDT(Request $request)
    {
        if ($request->user()->roles()->get()->contains('name', "admin")) {
            $EDT = EmploieDeTemp::with('classes')->orderBy('created_at', 'desc')
                ->paginate(5);
            if (empty($EDT)) {
                return response()->json(['message' => 'No Emploi de Temp found'], 404);
            }
            return response()->json($EDT, 200);
        } else {
            $EDT = $request->user()->EmploieDeTemp()->with('classes')->orderBy('updated_at', 'desc')->paginate(5);
            if (empty($EDT)) {
                return response()->json(['message' => 'No Emploi de Temp found'], 404);
            }
            return response()->json($EDT, 200);
        }
    }

    public function createEDT()
    {
        $EDT = new EmploieDeTemp();
        $EDT->Titre = "title";
        $EDT->classes_id = "classes_id";
        $EDT->file = "file";
        $EDT->idResponsableidResponsable = "idResponsable";
     
        $EDT->save();

        return response()->json($EDT, 201);
    }

    public function show()
    {
        $EDTs = EmploieDeTemp::orderBy('updated_at', 'desc')->paginate(5);
        if (sizeof($EDTs) > 0)
            return response()->json(
                $EDTs,
                200
            );
        else
            return response()->json([], 404);
    }

    public function showAll()
    {
        $EDT = EmploieDeTemp::all();
        if (sizeof($EDT) > 0) {
            return response()->json(["data" => $EDT], 200);
        } else
            return response()->json(
                [
                    "aucun Emploi de Temp"
                ],
                404
            );
    }

    public function addEDT(Request $request)
    {
        $EDT = new EmploieDeTemp();
        $EDT->titre = $request->input('title');
        $EDT->classes_id = $request->input('classes_id');
        $EDT->idResponsable = $request->user()->id;
        $EDT->file = $request->input('file')?$request->input('file'):null;

        $EDT->save();
        return response()->json([
            'type' => 'EDT',
            'message' => 'Emploi de Temp added',
            'attached' => true,
            "data" => EmploieDeTemp::find($EDT->id)
        ], 201);
    }

    public function updateEDT(Request $request, $id)
    {
        $EDTs = EmploieDeTemp::find($id);
        if ($EDTs) {
            $EDTs->title = $request->input('title') ? $request->input('title') : $EDTs->title;
            $EDTs->classes_id = $request->input('classes_id') ? $request->input('classes_id') : $EDTs->classes_id;
            $EDTs->file = $request->input('file') ? $request->input('file') : $EDTs->file;
            $EDTs->idResponsable = $request->input('idResponsable') ? $request->input('idResponsable') : $EDTs->type_cats_id;
            $EDTs->save();
            return response()->json([
                'message' => 'EDT mis à jour',
                'id' => $EDTs->id,
                'attributes' => $EDTs
            ], 201);
        } else {
            return response()->json([
                "EDT n existe pas"
            ], 404);
        }
    }

    public function deleteEDT($id)
    {
        $EDT = EmploieDeTemp::find($id);
        $EDT->delete();
        return response()->json([
            'type' => 'EDT',
            'message' => 'recalamation deleted',
            'attached' => true,
            "data" => EmploieDeTemp::find($id)
        ], 201);
    }
}
