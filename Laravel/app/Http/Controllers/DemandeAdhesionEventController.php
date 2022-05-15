<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\DemandeAdhesionEvent;

class DemandeAdhesionEventController extends Controller
{
    public function getDemandeAdhesion(Request $request){
        
      //  dd($request->user()->id);
        $demandeAdhesionEvent = DemandeAdhesionEvent::where('idStudent', $request->user()->id)->with('demandeEvent')->paginate();
        //dd($demandeAdhesionEvent);
     
        if (empty($demandeAdhesionEvent)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            return response()->json(["data" => $demandeAdhesionEvent], 200);
        }
    }

    public function createDemandeAdhesion(Request $request,$id){

        $demandeAdhesionEvent = new DemandeAdhesionEvent();
        $demandeAdhesionEvent->idStudent = $request->user()->id;
        $demandeAdhesionEvent->idEvent = $id;
        $demandeAdhesionEvent->save();
        return response()->json(["data" => $demandeAdhesionEvent], 201);
    }

    public function deleteDemandeAdhesion(Request $request,$id){

        $demandeAdhesionEvent = DemandeAdhesionEvent::where('id', $id);
        if (empty($demandeAdhesionEvent)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            $demandeAdhesionEvent->delete();
            return response()->json(["data" => $demandeAdhesionEvent], 200);
        }
    }

    public function updateDemandeAdhesion(Request $request,$id){

        $demandeAdhesionEvent = DemandeAdhesionEvent::find($id);
        
        //($demandeAdhesionEvent);
        if (empty($demandeAdhesionEvent)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            $demandeAdhesionEvent->idStudent = $request->user()->id;
            $demandeAdhesionEvent->idEvent = $request->input('idEvent');
            $demandeAdhesionEvent->save();
            return response()->json(["data" => $demandeAdhesionEvent], 200);
        }
    }


}
