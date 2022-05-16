<?php

namespace App\Http\Controllers;

use App\Models\club;
use App\Models\DemandeEvent;
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

    public function getDemandeAdhesionParClub(Request $Request ){
        $idResponsable = $Request->user()->id;
        $club = club::where('responsableClub', '=', $idResponsable)->first();
   
        $events = DemandeEvent::orderBy('updated_at','desc')
        ->where('clubId','=', $club->id)
        ->where('responsableClubId','=', $idResponsable)
        ->where('status','=',1)
        ->with('demandeAdhesionEvent')
        ->with('demandeAdhesionEvent.student')
        ->paginate(5);
        // $events = DemandeAdhesionEvent::orderBy('updated_at','desc')
        // ->with('demandeEvent')
        
        // // ->where('demande_events.responsableClubId','=', $idResponsable)
        // ->with('student')
        // // ->where('demandeEvent.clubId','=', $club->id)
        // ->get();
      //  dd($events->nomEvent);
        
     
        if (empty($events)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            return response()->json(["data" => $events], 200);
        }
    }
    public function accept(Request $request, $id)
    {
        
        $demande = DemandeAdhesionEvent::find($id);
        
       
        
        
            if (!$demande) {
                return response()->json("Not found", 404);
            } else {

                
                $demande->status = 1;
                $demande->save();
                return response()->json("Accepted", 200);
            }
       
    }
    public function decline(Request $request, $id)
    {
        $demande = DemandeAdhesionEvent::find($id);
        
        
            if (!$demande) {
                return response()->json("Not found", 404);
            } else {

               
                $demande->status = 2;
                $demande->save();
                return response()->json("Declined", 200);
            }
        
    }


}
