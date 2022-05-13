<?php

namespace App\Http\Controllers;

use App\Models\club;
use App\Models\DemandeEvent;
use Illuminate\Http\Request;
use App\Http\Requests\DemandeEventRequest;

class DemandeEventController extends Controller
{
    public function getDemandesEvent(Request $request)
    {
        //$Demandes = DemandeEvent::with('materiel')->where('id', '=', $id)->get();
        $idResponsable = $request->user()->id; 
       // dd($idResponsable);
        $club = club::where('responsableClub', '=', $idResponsable)->first();
    //    dd($club->id);
       // dd($club);
      // echo $club;
     // var_dump($club);
        $Demandes = DemandeEvent::where('responsableClubId','=', $idResponsable)->orderBy('updated_at','desc')->paginate(5);
        
       // dd($Demandes);
        if (sizeof($Demandes) > 0) {
            return response()->json(["data" => $Demandes], 200);
        } else
            return response()->json([
                "aucun évenement"
            ], 404);
    }
 
    public function createDemandeEvent(DemandeEventRequest $request){

        $nomEvent = $request->input('nomEvent');
        $dateEvent = $request->input('dateEvent');
        $description = $request->input('description');
        $responsableClubId = $request->user()->id;
        $club = club::where('responsableClub', '=', $responsableClubId)->first();
       
        $clubId = $club->id;

       // $clubId = this.getIdClub($request);
       // dd($clubId);
        $administrateurId = null;

        $newDemandeEvent = array(
            "nomEvent" => $nomEvent,
            "dateEvent" => $dateEvent,
            "description" => $description,
            "responsableClubId" => $responsableClubId,
            "administrateurId" => $administrateurId,
            "clubId" => $clubId
        );
        //echo $newDemandeEvent;
        $createEvents = DemandeEvent::create($newDemandeEvent);
       // dd($createEvents);

        if ($createEvents) {
            return response()->json([
                'data' => [
                    'message' => 'Demandes évenement created',
                    'id' => $createEvents->id,
                    'attributes' => $createEvents
                ]
            ], 201);
        }
        else {
            return response()->json([
                'data' => [
                    'message' => 'Demandes évenement not created',
                    'attributes' => $createEvents
                ]
            ], 404);
        }
        return "Event created";
    }
    // public function getEvent($id){
    //     $event = DemandeEvent::where('id', '=', $id)->first();
    //     if ($event) {
    //         return response()->json(["data" => $event], 200);
    //     } else
    //         return response()->json([
    //             "aucun évenement"
    //         ], 404);
    // }
    public function updateDemandeEvent(Request $request, $id)
    {
        $DemandeEvent = DemandeEvent::find($id);
        //dd($DemandeEvent);
        if ($DemandeEvent) {
            $DemandeEvent->nomEvent = $request->input('nomEvent') ? $request->input('nomEvent') : $DemandeEvent->nomEvent;
            $DemandeEvent->dateEvent = $request->input('dateEvent') ? $request->input('dateEvent') : $DemandeEvent->dateEvent;
            $DemandeEvent->description = $request->input('description') ? $request->input('description') : $DemandeEvent->description;
            $DemandeEvent->save();
            return response()->json([
                'message' => 'Update Success',
                'id' => $DemandeEvent->id,
                'attributes' => $DemandeEvent
            ], 201);
        } else {
            return response()->json([
                "demande non trouvée"
            ], 404);
        }
    }
    public function deleteDemandeEvent($id)
    {
        $DemandeEvent = DemandeEvent::find($id);
        if ($DemandeEvent) {
            $DemandeEvent->delete();
            return response()->json([], 204);
        } else {
            return response()->json([
                'type' => 'Demane$DemandeEvent',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }

   
}
