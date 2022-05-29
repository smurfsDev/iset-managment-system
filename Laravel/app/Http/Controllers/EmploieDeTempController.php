<?php

namespace App\Http\Controllers;

use App\Http\Requests\EmploieDeTempRequest;
use App\Models\EmploieDeTemp;
use App\Models\User;
use Illuminate\Http\Request;

class EmploieDeTempController extends Controller
{

    public function getEDT(Request $request)
    {
        if ($request->user()->roles()->get()->contains('name', "chefDepartement")) {
            $EDT = $request->user()->emploieDeTemp()->with("classes")->orderBy('created_at', 'desc')
                ->paginate(5);
            if (empty($EDT)) {
                return response()->json(['message' => 'No Emploi de Temp found'], 404);
            }
            return response()->json($EDT, 200);
        } else {
            $User = $request->user();
            $usr = User::with("classe")->find($User->id);
            $classId = $usr->classe[0]->roleUser->class->id;

            $EDT = EmploieDeTemp::where('classes_id', $classId)->with("classes")->orderBy('created_at', 'desc')
                ->first();
            if (empty($EDT)) {
                return response()->json([null], 404);
            }
            return response()->json($EDT, 200);
        }
    }

    public function addEDT(EmploieDeTempRequest $request)
    {
        $EDT = new EmploieDeTemp();
        $EDT->title = $request->input('title');
        $EDT->classes_id = $request->input('classes_id');
        $EDT->idResponsable = $request->user()->id;
        $EDT->file = $request->input('file') ? $request->input('file') : null;

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
