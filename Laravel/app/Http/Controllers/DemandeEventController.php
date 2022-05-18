<?php

namespace App\Http\Controllers;

use App\Models\club;
use App\Models\User;
use App\Models\DemandeEvent;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Models\DemandeAdhesionEvent;
use App\Http\Requests\DemandeEventRequest;

class DemandeEventController extends Controller
{
    public function index(Request $request){
        
        
        return $request->user()->DemandeEventResponsable()->get();
    }
    public function show(){
        //$demandes = DB::table('demande_events')->simplePaginate(5);
        $demandes = DemandeEvent::paginate(5);
        //$demandes = DemandeEvent::get();
        //gettype($demandes);
        if(sizeof($demandes)>0){
        return response()->json(["data"=>$demandes], 200);
        }
        else
            return response()->json([
                "aucune demande"
            ], 404);
    }

    public function getDemandesEvent(Request $request)
    {
        
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
    public function getEvent($id){
        $event = DemandeEvent::where('id', '=', $id)->first();
        if ($event) {
            return response()->json(["data" => $event], 200);
        } else
            return response()->json([
                "aucun évenement"
            ], 404);
    }
    public function getApprouvedEvent(Request $request){
        
       // $events = DemandeEvent::where('status', '=', '1')->paginate(5);
        // $apEvent = DemandeEvent::join('clubs','clubs.id', '=', 'demande_events.clubId')
        //         ->where('demande_events.status','=','1')
        //         ->get(['clubs.*','demande_events.*']);
        if ($request->user()->roles()->get()->contains('name', "student")) {
            $demandeAdhesionEvent = DemandeAdhesionEvent::where('idStudent', $request->user()->id)->with('demandeEvent')->get('idEvent');
            $apEvent = DemandeEvent::where('status',1)->whereNotIn('id',$demandeAdhesionEvent->flatten()->pluck('idEvent')->all())->with('club')->paginate(5);
        if (sizeof($apEvent) > 0) {
            return response()->json(["data" => $apEvent], 200);
        } else
            return response()->json([
                "aucun évenement"
            ], 404);
        }
        // $apEvent = DemandeEvent::where('status',1)->with('club')->paginate(5);
        //        // $demandes = $request->DemandeEvent()->club()->with('DemandeAdhesionClub')->with('DemandeAdhesionClub.user')->paginate(5);
        //       //  dd($apEvent);
        //     //  $apEvent->orderBy('updated_at','desc')->paginate(10);
        // if (sizeof($apEvent) > 0) {
        //     return response()->json(["data" => $apEvent], 200);
        // } else
        //     return response()->json([
        //         "aucun évenement"
        //     ], 404);
    }
    public function updateDemandeEvent(DemandeEventRequest $request, $id)
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

    public function accept(Request $request, $id)
    {
        
        $demande = DemandeEvent::find($id);
        
        $adminId = $request->user()->id;
        
        if (isset($adminId)) {
            if (!$demande) {
                return response()->json("Not found", 404);
            } else {

                $demande->administrateurId = $adminId;
                $demande->status = 1;
                $demande->save();
                return response()->json("Accepted", 200);
            }
        } else {
            return response()->json("Admin id required", 400);
        }
    }
    public function decline(Request $request, $id)
    {
        $demande = DemandeEvent::find($id);
        $adminId = $request->user()->id;
        if (isset($adminId)) {
            if (!$demande) {
                return response()->json("Not found", 404);
            } else {

                $demande->administrateurId = $adminId;
                $demande->status = 2;
                $demande->save();
                return response()->json("Declined", 200);
            }
        } else {
            return response()->json("Admin id required", 400);
        }
    }

   
}
